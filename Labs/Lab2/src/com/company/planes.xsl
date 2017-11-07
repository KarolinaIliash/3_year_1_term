<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Planes</h2>
                <table border="1">
                    <tr>
                        <th>Model</th>
                        <th>Origin</th>
                        <th>Type</th>
                        <th>Seats</th>
                        <th>Ammunition</th>
                        <th>Rackets</th>
                        <th>Radar</th>
                        <th>Length</th>
                        <th>Height</th>
                        <th>Wingspan</th>
                        <th>Price</th>
                    </tr>
                    <xsl:for-each select="planes/plane">
                        <tr>
                            <td><xsl:value-of select="@model"/></td>
                            <td><xsl:value-of select="origin"/></td>
                            <td><xsl:value-of select="chars/type"/></td>
                            <td><xsl:value-of select="chars/seats"/></td>
                            <td><xsl:value-of select="chars/ammunition"/></td>
                            <xsl:choose>
                                <xsl:when test = "chars/ammunition/@rackets">
                                    <td><xsl:value-of select="chars/ammunition/@rackets"/></td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td>0</td>
                                </xsl:otherwise>
                            </xsl:choose>
                            <td><xsl:value-of select="chars/radar"/></td>
                            <td><xsl:value-of select="parameters/length"/></td>
                            <td><xsl:value-of select="parameters/height"/></td>
                            <td><xsl:value-of select="parameters/wingspan"/></td>
                            <td><xsl:value-of select="price"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>